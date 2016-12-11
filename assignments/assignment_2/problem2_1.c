#include <stdio.h>
#include <stdlib.h>

typedef struct {
    char name[100];
    int comp, math, phys;
    int total;
} student ;

void scan_student(int, student*);
int scan_total_students();
student* allocate_students(int);
void input_info_students(int, student*);
void print_all(int, student*);
int compare_student_total(const void* student_a, const void* student_b);
void sort_student(int, student*);

int main(void) {

    /* int size_student = sizeof(all_student)/sizeof(all_student[0]); */
    int size_student = scan_total_students();
    student* all_student = allocate_students(size_student);
    input_info_students(size_student, all_student);
    sort_student(size_student, all_student);
    print_all(size_student, all_student);
    free(all_student);
    return 0;
}

int scan_total_students() {
    int student_amount = 0;
    printf("Enter how many students> ");
    scanf("%d", &student_amount);
    return student_amount;
}

void input_info_students(int student_size, student* student_array) {
    int i;
    for (i = 0; i < student_size; ++i) {
        scan_student(i, &student_array[i]);
    }

}

student* allocate_students(int student_amount) {
    return (student*) malloc(student_amount *sizeof(student));
}

void scan_student(int i, student* stu) {
    printf("Student %d's name> ", i+1);
    scanf("%s", stu->name);
    printf("Comp> ");
    scanf("%d", &stu->comp);
    printf("Phys> ");
    scanf("%d", &stu->phys);
    printf("Math> ");
    scanf("%d", &stu->math);
    stu->total = stu->comp + stu->math + stu->phys;
}

int compare_student_total(const void* student_a, const void* student_b) {
    student student_1 = *(const student*)student_a;
    student student_2 = *(const student*)student_b;
 
    if (student_1.total < student_2.total) {
        return 1;
    }

    if (student_2.total < student_1.total) {
        return -1;
    }
    return 0;
}

void sort_student(int student_size, student* student_array) {
    qsort(student_array, student_size, sizeof(student), compare_student_total);
}

void print_all(int student_size, student* student_array) {
    printf("=======================================\n");
    printf("Rank\tComp\tPhys\tMath\tTotal\tName\n");
    int i;
    for (i = 0; i < student_size; ++i) {
        printf("%d\t%d\t%d\t%d\t%d\t%s\n", i+1, student_array[i].comp, student_array[i].phys,student_array[i].math,student_array[i].total,student_array[i].name);
    }
}
