package hamza.quizmaker.models;

public class User {

    private String id;
    private String username;
    private String age;
    private String school;

    public User(String id, String username, String age, String school) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.school = school;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
