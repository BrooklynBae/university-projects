CREATE TYPE Health AS ENUM ('свеж', 'ощущает усталость');

CREATE TABLE Solar_system (
  id SERIAL PRIMARY KEY,
  planet_name VARCHAR(200) NOT NULL
);

CREATE TABLE Route (
  id SERIAL PRIMARY KEY,
  route_name VARCHAR(200),
  route_length INT NOT NULL,
  route_from INT REFERENCES Solar_system(id),
  route_to INT REFERENCES Solar_system(id),
  CONSTRAINT unique_route UNIQUE (route_from, route_to)
);

CREATE TABLE Cargo (
  id SERIAL PRIMARY KEY,
  cargo_name VARCHAR(200) NOT NULL
);

CREATE TABLE Crew (
  id SERIAL PRIMARY KEY,
  crew_name VARCHAR(200) NOT NULL,
  crew_health Health
);

CREATE TABLE Vehicle (
  id SERIAL PRIMARY KEY,
  vehicle_name VARCHAR(200) NOT NULL,
  vehicle_cargo INT NOT NULL REFERENCES Cargo(id),
  vehicle_route INT REFERENCES Route(id),
  vehicle_crew INT NOT NULL REFERENCES Crew(id),
  vehicle_ready_to_fly BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Route_of_vehicle (
  route_id INT NOT NULL REFERENCES Route(id),
  vehicle_id INT NOT NULL REFERENCES Vehicle(id),
  CONSTRAINT Route_of_vehicle_id PRIMARY KEY (route_id, vehicle_id)
);


CREATE OR REPLACE FUNCTION complex_vehicle_check() RETURNS TRIGGER AS $$
DECLARE
    crew_health_status Health;
    existing_route_count INT;
BEGIN
    SELECT crew_health INTO crew_health_status FROM Crew WHERE id = NEW.vehicle_crew;
    
    IF crew_health_status IS NULL OR crew_health_status != 'свеж' THEN
        crew_health_status := 'свеж';
        RAISE EXCEPTION 'Экипаж не готов к полету из-за плохого состояния здоровья';
    END IF;

    SELECT COUNT(*) INTO existing_route_count 
    FROM Route_of_vehicle 
    WHERE route_id = NEW.vehicle_route AND vehicle_id != NEW.id;
    
    IF existing_route_count > 0 THEN
        RAISE EXCEPTION 'Маршрут уже используется другим транспортным средством';
    END IF;

    NEW.vehicle_ready_to_fly := TRUE;
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_complex_vehicle_check
BEFORE INSERT OR UPDATE ON Vehicle
FOR EACH ROW
EXECUTE FUNCTION complex_vehicle_check();

INSERT INTO Vehicle(vehicle_name, vehicle_cargo, vehicle_route, vehicle_crew, vehicle_ready_to_fly)
VALUES ('Vasya', 200, 222, 5, FALSE);