import lombok.Getter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "timezoner")
@SessionScoped
public class TimezoneHandler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter
    private String timezone;

    public void setTimezone() {
        timezone = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("timezone");
    }
}
//Использование FacesContext позволяет интегрироваться в JSF для извлечения параметров из запроса.
//Аннотация @SessionScoped обеспечивает сохранение временной зоны на протяжении всей пользовательской сессии.
//Если клиент передаёт своё локальное время (вместе с меткой временной зоны), сервер сможет рассчитать точное время выполнения запроса с учётом разницы между локальным временем клиента и временем на сервере.