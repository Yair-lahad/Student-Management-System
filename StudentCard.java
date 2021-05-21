import java.util.Iterator;

public class StudentCard {
    private static final int DEFAULT_NOT_COMPLETED_GRADE = -100;
    private String name;
    private int id;
    private double average;
    private int numOfCompletedCourses;
    private List<SimpleIntPair> courses;

    public StudentCard(String name, int id) {
        this.name = name;
        this.id = id;
        this.numOfCompletedCourses = 0;
        this.average = -50;
        this.courses = new DynamicArray<>();
    }

    /**
     * A getter of the field name.
     *
     * @return the field name..
     */
    public String getName() {
        return name;
    }

    /**
     * A getter of the field ID.
     *
     * @return the field ID..
     */
    public int getId() {
        return id;
    }


    /**
     * A getter of the field average.
     *
     * @return the field average.
     */
    public double getAverage() {
        return average;
    }


    public int hasCourse(int course) {
        boolean has=false;
        int index=-1;
        for(int i=0; i<courses.size() &!has;i++){            // loop through courses array to find if course including in it
            if(courses.get(i).getFirst()==course) {
                has=true;
                index=i;
            }
        }
        return index;
    }


    public boolean isActive() {
        boolean active=false;
        for (int i=0;i<courses.size() & !active;i++){
            active= courses.get(i).getSecond()==DEFAULT_NOT_COMPLETED_GRADE; //active course only if grades has not changed
        }
        return active;
    }


    public int courseGrade(int course) {
        if(hasCourse(course)==-1) throw new IllegalArgumentException("Can not check grade- Student Do not study course");
        return courses.get(hasCourse(course)).getSecond();
    }


    public boolean registerCourse(int course) {
        boolean notReg = (hasCourse(course)==-1);    // course does not exist in student card
        if(courses.size()==0){  // add if list is empty
            SimpleIntPair addcourse = new SimpleIntPair(course, DEFAULT_NOT_COMPLETED_GRADE);
            courses.add(addcourse);
        }
        else if(notReg) { // if not registered, add course in the right order
            int index=0;
            for(int i=0;i<courses.size() &!notReg ;i++) {
                if (courses.get(i).getFirst() < courses.get(hasCourse(course)).getFirst()) {
                    index = i;
                }
            }
            SimpleIntPair addcourse = new SimpleIntPair(course, DEFAULT_NOT_COMPLETED_GRADE);
            courses.add(index, addcourse);
        }
        return notReg; // returns true if added or false if not added
    }


    public boolean completeCourse(int course, int grade) {
        if(grade<0 | grade>100) throw new IllegalArgumentException(" course grade should be between 0-100");
        boolean hascourse=true;
        // course does not exists
        if (hasCourse(course)==-1)
            hascourse=false;

        // the course exists, student finished it and got a grade for the course
        else if(courses.get(hasCourse(course)).getSecond()!=DEFAULT_NOT_COMPLETED_GRADE)
            hascourse=false;

        // update grade, number of complete courses and total average of student
        else{
            courses.get(hasCourse(course)).setSecond(grade);
            numOfCompletedCourses++;
            average= (average*(numOfCompletedCourses-1)+grade)/numOfCompletedCourses;
        }
        return hascourse;

    }


    @Override
    public String toString() {
        return getName() + " " + getId();
    }

}

