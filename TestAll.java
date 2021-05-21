import java.util.Comparator;

public class TestAll {
    public static void main(String[] args) {
        // Student card
        StudentCard studentCard1 = new StudentCard("One", 1);
        test("Student card "+studentCard1, studentCard1.toString().equals("One 1"), 10);
        test("Student is active "+studentCard1.isActive(), studentCard1.isActive() == false, 15);
        studentCard1.registerCourse(3);
        studentCard1.registerCourse(2);
        studentCard1.registerCourse(1);
        test("course 1 is in "+studentCard1.hasCourse(1), studentCard1.hasCourse(1) == 0, 20);
        test("course 2 is in "+studentCard1.hasCourse(2), studentCard1.hasCourse(2) == 1, 30);
        test("course 3 is in "+studentCard1.hasCourse(3), studentCard1.hasCourse(3) == 2, 40);
        test("Student is active "+studentCard1.isActive(), studentCard1.isActive() == true, 50);
        test("course 1 grade "+studentCard1.courseGrade(1), studentCard1.courseGrade(1) == -100, 60);
        test("Aveage "+studentCard1.getAverage(), studentCard1.getAverage() == -50, 70);
        studentCard1.completeCourse(1,70);
        test("Aveage "+studentCard1.getAverage(), studentCard1.getAverage() == 70, 80);
        studentCard1.completeCourse(2,80);
        test("Aveage "+studentCard1.getAverage(), studentCard1.getAverage() == 75, 90);
        studentCard1.completeCourse(3,90);
        test("Aveage "+studentCard1.getAverage(), studentCard1.getAverage() == 80, 100);
        test("grade of course 3 "+studentCard1.courseGrade(3), studentCard1.courseGrade(3) == 90, 110);
        // Comparators
        StudentCard studentCard2 = new StudentCard("Two", 2);
        StudentComparatorById idComparator = new StudentComparatorById();
        test("compare student 1 with 2 = "+idComparator.compare(studentCard1, studentCard2),
                idComparator.compare(studentCard1, studentCard2) == -1, 120);
        test("compare student 1 with 1 = "+idComparator.compare(studentCard1, studentCard1),
                idComparator.compare(studentCard1, studentCard1) == 0, 130);
        test("compare student 2 with 1 = "+idComparator.compare(studentCard2, studentCard1),
                idComparator.compare(studentCard2, studentCard1) == 1, 140);
        StudentComparatorByAverage averageComparator = new StudentComparatorByAverage();
        test("Avg. compare student 1 with 2 = "+averageComparator.compare(studentCard1, studentCard2),
                averageComparator.compare(studentCard1, studentCard2) == 1, 150);
        test("Avg. compare student 1 with 1 = "+averageComparator.compare(studentCard1, studentCard1),
                averageComparator.compare(studentCard1, studentCard1) == 0, 160);
        test("Avg. compare student 2 with 1 = "+averageComparator.compare(studentCard2, studentCard1),
                averageComparator.compare(studentCard2, studentCard1) == -1, 170);
        studentCard2.registerCourse(2);
        studentCard2.completeCourse(2,80);
        test("Avg. compare student 2 with 1 = "+averageComparator.compare(studentCard2, studentCard1),
                averageComparator.compare(studentCard2, studentCard1) == 1, 180);
        // Binary Trees
        Comparator ic = new NumberComparator();
        Comparator sc = new StringComparator();
        BinarySearchTree t1 = new BinarySearchTree(ic);
        t1.insert(2);
        t1.insert(1);
        t1.insert(3);
        t1.insert(4);
        test("t1 = \n"+t1, t1.toString().equals("\\-- 2\n    |-- 1\n    \\-- 3\n        \\-- 4\n"), 190);
        BinarySearchTree t2 = new BinarySearchTree(ic);
        t2.insert(40);
        t2.insert(10);
        t2.insert(20);
        t2.insert(70);
        t2.insert(50);
        test("t2 = \n"+t2, t2.toString().equals("\\-- 40\n    |-- 10\n    |   \\-- 20\n    \\-- 70\n        |-- 50\n"), 200);
        BinarySearchTree t3 = new BinarySearchTree(sc);
        t3.insert("hello");
        t3.insert("world");
        t3.insert("good");
        t3.insert("luck!");
        test("t3 = \n"+t3, t3.toString().equals("\\-- hello\n    |-- good\n    \\-- world\n        |-- luck!\n"), 210);
        // Balancing
        StudentCardBinarySearchTree scbst = new StudentCardBinarySearchTree(idComparator);
        StudentCard studentCard3 = new StudentCard("Three", 3);
        StudentCard studentCard4 = new StudentCard("Four", 4);
        StudentCard studentCard5 = new StudentCard("Five", 5);
        scbst.insert(studentCard1);
        scbst.insert(studentCard2);
        scbst.insert(studentCard3);
        scbst.insert(studentCard4);
        scbst.insert(studentCard5);
        test("\n"+scbst+ " is balanced = "+isBalance(scbst), isBalance(scbst)==false, 220);
        scbst.balance();
        test("\n"+scbst+ " is balanced = "+isBalance(scbst), isBalance(scbst)==true, 230);
        //Filters
        FilterByCourse fbc = new FilterByCourse(1);
        test(studentCard1+" learned course 1 "+fbc.accept(studentCard1), fbc.accept(studentCard1) == true, 240);
        studentCard2.registerCourse(1);
        test(studentCard2+" learned course 1 "+fbc.accept(studentCard2), fbc.accept(studentCard2) == false, 250);
        FilterByActive fba = new FilterByActive();
        test(studentCard1+" is active = "+fba.accept(studentCard1), fba.accept(studentCard1)==false, 260);
        test(studentCard2+" is active = "+fba.accept(studentCard2), fba.accept(studentCard2)==true, 270);
        test(studentCard3+" is active = "+fba.accept(studentCard3), fba.accept(studentCard3)==false, 280);
        // Iterator
        FilteredStudentCardIterator fsci = new FilteredStudentCardIterator(scbst, fba);
        DynamicArray<StudentCard> students = new DynamicArray<>();
        while (fsci.hasNext())
            students.add(fsci.next());
        test("students size is "+students.size(), students.size() == 1, 290);
        studentCard3.registerCourse(1);
        fsci = new FilteredStudentCardIterator(scbst, fba);
        students = new DynamicArray<>();
        while (fsci.hasNext())
            students.add(fsci.next());
        test("students size is "+students.size(), students.size() == 2, 300);
        test("students.get(1) is "+studentCard3, students.get(1).equals(studentCard3), 310);
        // University
        University university = new University();
        university.add(studentCard1);
        university.add(studentCard2);
        university.add(studentCard3);
        university.add(studentCard4);
        university.add(studentCard5);
        test("course 1 average = "+university.courseAverage(1),university.courseAverage(1) == 70, 320);
        university.register(studentCard3.getId(),2);
        university.complete(studentCard3.getId(), 2, 50);
        test("course 2 average = "+university.courseAverage(2),university.courseAverage(2) == 70, 330);
        university.delete(studentCard2);
        test("course 2 average = "+university.courseAverage(2),university.courseAverage(2) == 65, 340);
        students = new DynamicArray<>();
        university.activeStudentsByAverage();
        university.register(1,6);
        System.out.println("--------------------- ");
        university.activeStudentsByAverage();
    }
    public static <T> boolean isBalance(BinaryTree<T> t) {
        if (!t.isEmpty())
            return checkBalance(t.root);
        else
            return true;
    }

    private static <T> boolean checkBalance(BinaryNode<T> root) {

        if (root.left == null | root.right == null)
            return root.height() <= 2;
        if (root.left != null & root.right != null)
            return (Math.abs(root.left.height() - root.right.height()) <= 1) &
                    checkBalance(root.left) & checkBalance(root.right);

        //should not get here
        return false;
    }

    private static int nFailures = 0;
    public static void test(String sufix, boolean term, int id) {
        if (term)
            System.out.println(" Test no. " + id + " OK. Overall " + nFailures + " failures " + sufix);
        else {
            nFailures = nFailures + 1;
            System.out.println(" Test no. " + id + " failed. Overall " + nFailures + " failures " + sufix);
        }
    }
}
