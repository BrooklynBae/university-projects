package model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvRecurse;
import enums.Semester;

import java.io.Serializable;
import java.util.Date;

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

    public StudyGroup(Long id, String owner, String name, Coordinates coordinates, java.sql.Date creationDate, Long studentsCount, Integer shouldBeExpelled, float averageMark, Semester semesterEnum, Person groupAdmin) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentsCount = studentsCount;
        this.shouldBeExpelled = shouldBeExpelled;
        this.averageMark = averageMark;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwner(String owner) { this.owner = owner; }

    public String getOwner() { return owner; }

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

    public Date getCreationDate() {
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

    public static String toString(StudyGroup studyGroup) {
        String ans = "";
        ans = ans + ("Owner = " + studyGroup.getOwner() + ", Id = " + studyGroup.getId() + ", Name = " + studyGroup.getName() + ", Coordinates: X = " + studyGroup.getCoordinates().getX() + ", Y = " + studyGroup.getCoordinates().getY()
                + ", Creation Date + " + studyGroup.getCreationDate()
                + ", Students count = " + studyGroup.getStudentsCount() + ", Should be expelled = " + studyGroup.getShouldBeExpelled() + ", Average mark = " + studyGroup.getAverageMark()
                + ", Semester Enum = " + studyGroup.getSemesterEnum());

        if (studyGroup.getGroupAdmin() != null) {
            ans = ans + ("    Group admin: Name = " + studyGroup.getGroupAdmin().getName() + ", Birthday = " + studyGroup.getGroupAdmin().getBirthday()
                    + ", Hair color = "
                    + studyGroup.getGroupAdmin().getHairColor() + ", Nationality = " + studyGroup.getGroupAdmin().getNationality());
        } else {
            ans = ans + ("  Group admin = null");
        }
        return ans;
    }
}

//package model;
//
//import com.opencsv.bean.CsvDate;
//import com.opencsv.bean.CsvRecurse;
//import enums.Semester;
//import lombok.Data;
//import lombok.Getter;
//
//import java.io.Serializable;
//
//@Data
//public class StudyGroup implements Serializable {
//    private static final long serialVersionUID = 454165465L;
//
//    private Long id = 1L;
//
//    private String name;
//
//    private Coordinates coordinates;
//
////    @CsvDate(value = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSSSSS][.SSSSSS][.SSS]")
//    @CsvDate
//    private java.util.Date creationDate;
//
//    private Long studentsCount;
//
//    private Integer shouldBeExpelled;
//
//    private float averageMark;
//
//    private Semester semesterEnum;
//
//    private Person groupAdmin;
//
//    public StudyGroup() {
//    }
//
//    public StudyGroup(Long id, String name, Coordinates coordinates, java.util.Date creationDate, Long studentsCount, Integer shouldBeExpelled, float averageMark, Semester semesterEnum, Person groupAdmin) {
//        this.id = id;
//        this.name = name;
//        this.coordinates = coordinates;
//        this.creationDate = creationDate;
//        this.studentsCount = studentsCount;
//        this.shouldBeExpelled = shouldBeExpelled;
//        this.averageMark = averageMark;
//        this.semesterEnum = semesterEnum;
//        this.groupAdmin = groupAdmin;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setCoordinates(Coordinates coordinates) {
//        this.coordinates = coordinates;
//    }
//
//    public void setCreationDate(java.util.Date creationDate) {
//        this.creationDate = creationDate;
//    }
//
//    public void setStudentsCount(Long studentsCount) {
//        this.studentsCount = studentsCount;
//    }
//
//    public void setShouldBeExpelled(Integer shouldBeExpelled) {
//        this.shouldBeExpelled = shouldBeExpelled;
//    }
//
//    public void setAverageMark(float averageMark) {
//        this.averageMark = averageMark;
//    }
//
//    public void setSemesterEnum(Semester semesterEnum) {
//        this.semesterEnum = semesterEnum;
//    }
//
//    public void setGroupAdmin(Person groupAdmin) {
//        this.groupAdmin = groupAdmin;
//    }
//
//    public int compareTo(StudyGroup o) {
//        return Long.compare(this.studentsCount, o.studentsCount);
//    }
//
//
//    public static String toString(StudyGroup studyGroup) {
//        String ans = "";
//        ans = ans + ("Id = " + studyGroup.getId() + ", Name = " + studyGroup.getName() + ", Coordinates: X = " + studyGroup.getCoordinates().getX() + ", Y = " + studyGroup.getCoordinates().getY()
//                + ", Creation Date + " + studyGroup.getCreationDate()
//                + ", Students count = " + studyGroup.getStudentsCount() + ", Should be expelled = " + studyGroup.getShouldBeExpelled() + ", Average mark = " + studyGroup.getAverageMark()
//                + ", Semester Enum = " + studyGroup.getSemesterEnum());
//
//        if (studyGroup.getGroupAdmin() != null) {
//            ans = ans + ("    Group admin: Name = " + studyGroup.getGroupAdmin().getName() + ", Birthday = " + studyGroup.getGroupAdmin().getBirthday()
//                    + ", Eye color = " + studyGroup.getGroupAdmin().getEyeColor() + ", Hair color = "
//                    + studyGroup.getGroupAdmin().getHairColor() + ", Nationality = " + studyGroup.getGroupAdmin().getNationality());
//        } else {
//            ans = ans + ("  Group admin = null");
//        }
//        return ans;
//    }
//}
