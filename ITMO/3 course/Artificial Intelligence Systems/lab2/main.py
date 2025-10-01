import re
from swiplserver import PrologMQI, create_posix_path
from src.methods.find_parents import FindParents
from src.methods.find_children import FindChildren

KNOWLEDGE_BASE_PATH = "./src/prolog/family.pl"

requests = [
    "Кто родители galina?",
    "Кто дети alexey?",
    "Кто братья и сестры valentina?",
    "Кто бабушки/дедушки vlad?",
    "Кто старше: kirill или marina?",
    "Кто ровесники kseniya?",
    "Женился ли andrey до 2005 года?"
    "kseniya и marina родные сестры/братья?",
    "kseniya бабушка vlad?",
    "У daria есть родные братья/сёстры?",
    "olga – свекровь anna?",
    "Перечислить браки, заключенные до 2000-го года",
    "roza – предок kseniya?",
    "Были ли anna и andrey когда-либо женаты?",
    "ariana старше denis?",
    "Перечислить всех людей, у которых нет родных братьев/сестер, и они младше 2005 года"
]

patterns = {
    r"Кто родители (.+)\?": FindParents,
    r"Кто дети (.+)\?": FindChildren,
}


def start():
    with PrologMQI() as mqi:
        with mqi.create_thread() as prolog:
            path = create_posix_path(KNOWLEDGE_BASE_PATH)
            prolog.query(f'consult("{path}")')

            print("Система готова к работе")
            print("Примеры запросов:", "\n * " + ("\n * ".join(requests)))
            print("Для завершения введите exit")

            while True:
                user_input = input("> ")
                if user_input.lower() == "exit":
                    break

                for pattern in patterns:
                    match = re.match(pattern, user_input, re.IGNORECASE)
                    if match is None:
                        continue

                    processor_class = patterns[pattern]
                    processor = processor_class(*match.groups())
                    processor.run(prolog)
                    break
                else:
                    print("One of us is a dumbass, follow request examples")


if __name__ == "__main__":
    start()