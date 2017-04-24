package com.gmail.s12348.evgen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Group implements Voencom, Serializable {

	private static final long serialVersionUID = 1L;
	private List<Student> studentGroup = new ArrayList<Student>();

	public Group() {

	}

	public void enterStudent() {
		for (;;) {
			int s = JOptionPane.showConfirmDialog(null, "Want to enter a new student into the group?");
			if (s == 0) {

				zanesenieStudent(proverkaGroup());

			} else {
				break;
			}
		}
	}

	public int proverkaGroup() {
		int i = 0;
		for (i = 0; i < studentGroup.size();) {
			if (studentGroup.get(i) != null) {
				i += 1;
			} else {
				break;
			}

		}
		return i;
	}

	public void zanesenieStudent(int i) {
		try {
			Student student = new Student();
			student.inputSurname();			
			student.inputName();
			student.inputSex();
			student.inputOld();
			student.inputHighSchool();
			student.inputDepartment();
			student.inputKurs();
			student.inputType();
			student.inputAssessment();
			studentGroup.add(i, student);
		} catch (MyExeption e) {
			e.nameNull("null");
		}
	}

	public void requestDeleteStudent() {
		for (;;) {
			try {
				int n = Integer.valueOf(JOptionPane.showInputDialog(
						"Enter the number of the record you want to delete (From 1 to " + studentGroup.size() + ")."));
				if (n < 1 & n > studentGroup.size())
					throw new MyNegativeExeption();
				deleteStudent(n);
				break;
			} catch (MyNegativeExeption e) {
				e.negativeNumber();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error number format");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Canseled set as default");
				break;
			}
		}

	}

	public void deleteStudent(int n) {
		studentGroup.remove(n - 1);
	}

	public String zaprosPoiskStudent() {
		String surname = "";
		for (;;) {
			try {
				surname = String
						.valueOf(JOptionPane.showInputDialog("Enter the surname of the student you want to find."));
				if (surname == "null")
					throw new MyExeption();
				if (checkString(surname) != true) {
					JOptionPane.showMessageDialog(null, "Input Error");
				} else {
					break;
				}
			} catch (MyExeption e) {
				e.nameNull("null");
			}
		}
		return surname;
	}

	public void poiskStudent(String surname) {
		int k = 0;
		for (int i = 0; i < studentGroup.size(); i++) {
			if (studentGroup.get(i) != null) {
				if (studentGroup.get(i).getSurname().compareToIgnoreCase(surname) == 0) {
					System.out.println(studentGroup.get(i));
					System.out.println();
					k += 1;
				}
			}
		}
		if (k == 0) {
			JOptionPane.showMessageDialog(null, "Нет такого студента");
		}
	}

	public void sortArray() {
		int n = 0;
		for (;;) {
			try {
				n = Integer.valueOf(
						JOptionPane.showInputDialog("Select by what to sort (1-name, 2-surname, 3-sex, 4-age)."));
				if (n < 1 & n > 4)
					throw new MyNegativeExeption();
				break;
			} catch (MyNegativeExeption e) {
				e.negativeNumber();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error number format");
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "Canseled set as default");
				break;
			}
		}
		if (studentGroup.size() > 1 | studentGroup != null) {
			switch (n) {
			case 1:
				Collections.sort(studentGroup, new Comparator<Student>() {
					public int compare(Student a, Student b) {
						return a.getName().compareToIgnoreCase(b.getName());
					}
				});
				break;
			case 2:
				Collections.sort(studentGroup, new Comparator<Student>() {
					public int compare(Student a, Student b) {
						return a.getSurname().compareToIgnoreCase(b.getSurname());
					}
				});
				break;
			case 3:
				Collections.sort(studentGroup, new Comparator<Student>() {
					public int compare(Student a, Student b) {
						return a.getSex().compareToIgnoreCase(b.getSex());
					}
				});
				break;
			case 4:
				Collections.sort(studentGroup, new Comparator<Student>() {
					public int compare(Student a, Student b) {
						return Integer.compare(a.getOld(), b.getOld());
					}
				});
				break;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Can not be sorted!");
		}
	}

	public boolean checkString(String string) {
		if (string.length() == 0) {
			return false;
		} else {
			Pattern p = Pattern.compile("^([a-zA-Z- №]+)");
			Matcher m = p.matcher(string);

			return m.matches();
		}
	}

	@Override
	public String toString() {
		int k = 0;
		for (int i = 0; i < studentGroup.size(); i++) {
			if (studentGroup.get(i) != null) {
				System.out.println(studentGroup.get(i) + " ");
				k += 1;
			}
		}

		return "In Group [" + k + "]";
	}

	@Override
	public void prizivnik() {
		Student[] prizivnik = new Student[studentGroup.size()];
		for (int i = 0; i < studentGroup.size(); i++) {
			if (studentGroup.get(i) != null) {
				if (studentGroup.get(i).getOld() > 18 & studentGroup.get(i).getSex().compareTo("man") == 0) {
					for (int j = 0; j < prizivnik.length; j++) {
						if (prizivnik[j] == null) {
							prizivnik[j] = studentGroup.get(i);
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < prizivnik.length; i++) {
			if (prizivnik[i] != null) {
				System.out.println(prizivnik[i]);
			}
		}
	}

}
