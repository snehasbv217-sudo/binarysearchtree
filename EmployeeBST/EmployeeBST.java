import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

class Node {
    Employee employee;
    Node left;
    Node right;

    Node(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}

class EmployeeTree {

    Node root;

    // Insert employee
    Node insert(Node root, Employee employee) {

        if (root == null) {
            return new Node(employee);
        }

        if (employee.id < root.employee.id) {
            root.left = insert(root.left, employee);
        } else if (employee.id > root.employee.id) {
            root.right = insert(root.right, employee);
        } else {
            System.out.println("Employee ID already exists.");
        }

        return root;
    }

    // Search employee
    Node search(Node root, int id) {

        if (root == null || root.employee.id == id) {
            return root;
        }

        if (id < root.employee.id) {
            return search(root.left, id);
        }

        return search(root.right, id);
    }

    // Find minimum node
    Node findMinimum(Node root) {

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    // Delete employee
    Node delete(Node root, int id) {

        if (root == null) {
            return null;
        }

        if (id < root.employee.id) {
            root.left = delete(root.left, id);
        } 
        else if (id > root.employee.id) {
            root.right = delete(root.right, id);
        } 
        else {

            // No child
            if (root.left == null && root.right == null) {
                return null;
            }

            // Only right child
            if (root.left == null) {
                return root.right;
            }

            // Only left child
            if (root.right == null) {
                return root.left;
            }

            // Two children
            Node minimum = findMinimum(root.right);

            root.employee = minimum.employee;

            root.right = delete(root.right, minimum.employee.id);
        }

        return root;
    }

    // Inorder traversal
    void inorder(Node root) {

        if (root != null) {
            inorder(root.left);
            display(root.employee);
            inorder(root.right);
        }
    }

    // Preorder traversal
    void preorder(Node root) {

        if (root != null) {
            display(root.employee);
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder traversal
    void postorder(Node root) {

        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            display(root.employee);
        }
    }

    // Display employee
    void display(Employee employee) {

        System.out.println(
                "ID: " + employee.id +
                " | Name: " + employee.name +
                " | Salary: " + employee.salary
        );
    }
}

public class EmployeeBST {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EmployeeTree tree = new EmployeeTree();

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("   EMPLOYEE BINARY SEARCH TREE");
            System.out.println("=================================");
            System.out.println("1. Insert Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Employee Salary: ");
                    double salary = scanner.nextDouble();

                    Employee employee =
                            new Employee(id, name, salary);

                    tree.root = tree.insert(tree.root, employee);

                    System.out.println("Employee inserted successfully.");

                    break;

                case 2:

                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();

                    Node result = tree.search(tree.root, searchId);

                    if (result != null) {
                        System.out.println("\nEmployee Found:");
                        tree.display(result.employee);
                    } else {
                        System.out.println("Employee not found.");
                    }

                    break;

                case 3:

                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = scanner.nextInt();

                    if (tree.search(tree.root, deleteId) != null) {

                        tree.root = tree.delete(tree.root, deleteId);

                        System.out.println("Employee deleted successfully.");

                    } else {
                        System.out.println("Employee not found.");
                    }

                    break;

                case 4:

                    System.out.println("\n--- INORDER TRAVERSAL ---");

                    if (tree.root == null) {
                        System.out.println("Tree is empty.");
                    } else {
                        tree.inorder(tree.root);
                    }

                    break;

                case 5:

                    System.out.println("\n--- PREORDER TRAVERSAL ---");

                    if (tree.root == null) {
                        System.out.println("Tree is empty.");
                    } else {
                        tree.preorder(tree.root);
                    }

                    break;

                case 6:

                    System.out.println("\n--- POSTORDER TRAVERSAL ---");

                    if (tree.root == null) {
                        System.out.println("Tree is empty.");
                    } else {
                        tree.postorder(tree.root);
                    }

                    break;

                case 7:

                    System.out.println("Program ended.");
                    break;

                default:

                    System.out.println("Invalid choice. Please enter 1 to 7.");
            }

        } while (choice != 7);

        scanner.close();
    }
}