/*
I, Yair Lahad (205493018), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Comparator;
import java.util.NoSuchElementException;

public class University {
    private StudentCardBinarySearchTree idTree;
    private StudentCardBinarySearchTree avgTree;

    public University() {
        idTree = new StudentCardBinarySearchTree(new StudentComparatorById());
        avgTree = new StudentCardBinarySearchTree(new StudentComparatorByAverage());
    }

    public StudentCard lookUp(int idNumber) {
        StudentCard lookFor = new StudentCard("", idNumber);
        return idTree.findData(lookFor);
    }

    public void balance() {
        idTree.balance();
        avgTree.balance();
    }

    public boolean add(StudentCard newStudent) {
        boolean ans=false;
        if(lookUp(newStudent.getId())==null) {
            idTree.insert(newStudent);
            avgTree.insert(newStudent);
            ans=true;
        }
        return ans;
    }


    public boolean delete(StudentCard student) {
        boolean output=false;
        StudentCard remove=lookUp(student.getId()); //returns student card with matching id
        if (remove!=null){
            // removing from both trees
            idTree.remove(remove);
            avgTree.remove(remove);
            output=true;
        }
        return output;
    }

    public boolean register(int id, int curse) {
        boolean isReg=false;
        StudentCard toReg=lookUp(id); // returns student card with matching id
        if(toReg!=null)
             isReg=toReg.registerCourse(curse); //adding student course and return boolean value
        return isReg;
    }

    public boolean complete(int id, int curse, int grade) {
        boolean output=false;
        StudentCard comp=lookUp(id);
        if(comp!=null){
            delete(comp);
            output=comp.completeCourse(curse,grade);
            add(comp);
        }
        return output;

    }

    public double courseAverage(int course) {
        double sum=0;
        double count=0; //number of students
        for(StudentCard stud : avgTree){
            int index=stud.hasCourse(course);
            if(index!=-1){
                double grade=stud.courseGrade(course);
                if(grade>=0){
                    sum=sum+grade;
                    count++;
                }
            }
        }
        if(count==0)
            throw new NoSuchElementException("no one finished this course");
        return sum/count;
    }

    public void activeStudentsByAverage() {
        FilterByActive fil= new FilterByActive();
        FilteredStudentCardIterator it= new FilteredStudentCardIterator(avgTree,fil); //creating new filtered iterator usiing filter by active
        StackAsDynamicArray<StudentCard> st= new StackAsDynamicArray<>();
        while(it.hasNext()){
            st.push(it.next());
        }
        while(!st.isEmpty()) {
            System.out.println(st.pop());   //printing the values in the correct order by using stack properties
        }
    }
}
