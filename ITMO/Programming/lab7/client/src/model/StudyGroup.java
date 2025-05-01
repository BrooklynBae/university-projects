package model;

import enums.Semester;

import java.io.Serializable;

public class StudyGroup implements Serializable {
    private static final long serialVersionUID = 454165465L;
    private Long id = 1L;

    private String owner;

    private String name;

    private Coordinates coordinates;

    private java.sql.Date creationDate;

    private Long studentsCount;

    private Integer shouldBeExpelled;

    private float averageMark;

    private Semester semesterEnum;

    private Person groupAdmin;



    public StudyGroup() {
    }

    public StudyGroup(Long id, String name, Coordinates coordinates, java.sql.Date creationDate, Long studentsCount, Integer shouldBeExpelled, float averageMark, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.sql.Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(Long studentsCount) {
        this.studentsCount = studentsCount;
    }

    public Integer getShouldBeExpelled() {
        return shouldBeExpelled;
    }

    public void setShouldBeExpelled(Integer shouldBeExpelled) {
        this.shouldBeExpelled = shouldBeExpelled;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public void setSemesterEnum(Semester semesterEnum) {
        this.semesterEnum = semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(Person groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public int compareTo(StudyGroup o) {
        return Long.compare(this.studentsCount, o.studentsCount);
    }


    public static void toString(StudyGroup studyGroup) {
        System.out.println("Id = " + studyGroup.getId() + ", Name = " + studyGroup.getName() + ", Coordinates: X = " + studyGroup.getCoordinates().getX() + ", Y = " + studyGroup.getCoordinates().getY()
                + ", Creation Date + " + studyGroup.getCreationDate()
                + ", Students count = " + studyGroup.getStudentsCount() + ", Should be expelled = " + studyGroup.getShouldBeExpelled() + ", Average mark = " + studyGroup.getAverageMark()
                + ", Semester Enum = " + studyGroup.getSemesterEnum());

        if (studyGroup.getGroupAdmin() != null) {
            System.out.println("    Group admin: Name = " + studyGroup.getGroupAdmin().getName() + ", Birthday = " + studyGroup.getGroupAdmin().getBirthday()
                    +  "Hair color = "
                    + studyGroup.getGroupAdmin().getHairColor() + ", Nationality = " + studyGroup.getGroupAdmin().getNationality());
        } else {
            System.out.print("  Group admin = null");
        }
    }
}
