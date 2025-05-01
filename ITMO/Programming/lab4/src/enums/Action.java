package enums;

public enum Action {
    WANDER("бродил"),
    WENT("отправился"),
    DO("делать"),
    BREAK("разобьет"),
    STOP_SLEEP("перестанет ночевать"),
    FETCH_A_BREATH("вздохнул"),
    JUMP("подпрыгнул"),
    COME_UP("подошел"),
    GLANCE("выглянул"),
    MEAN("означал"),
    STAND("стоял"),
    RETURN("не возвращался"),
    UNDERSTANDABLE ("понятно"),
    ACCURED ("взялись"),
    STANDING ("стоя"),
    LOOK ("глядя"),
    SWIM_AWAY ("уплывала"),
    WERE ("были"),
    LAY ("лежал"),
    WATCH ("смотрел"),
    BLOOM ("благоухали"),
    GET_RID_OF ("избавляться"),
    TO_COBWEB ("Путается"),
    PERSIST ("упорствовал"),
    CANT_SLEEP ("не спалось"),
    RANG_OUT("раздался");

    public final String definition;

    Action(String description) {
        this.definition = description;
    }
}
