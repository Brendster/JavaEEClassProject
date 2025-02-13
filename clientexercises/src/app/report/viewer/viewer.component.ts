import { Component, OnDestroy, OnInit } from '@angular/core';
import { Employee } from '@app/employee/employee';
import { NewEmployeeService } from '@app/employee/newemployee.service';
import { Expense } from '@app/expense/expense';
import { Report } from '../report';
import { ReportItem } from '../report-item';
import { ExpenseService } from '@app/expense/expense.service';
import { Subscription } from 'rxjs';
import { PDFURL } from '@app/constants';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MatComponentsModule } from '@app/mat-components/mat-components.module';
import { ReportService } from '../report.service';

@Component({
  selector: 'app-viewer',
  standalone: true,
  imports: [CommonModule, MatComponentsModule, ReactiveFormsModule],
  templateUrl: './viewer.component.html',
})
export class ViewerComponent implements OnInit, OnDestroy {
  // form
  viewerForm: FormGroup;
  employeeid: FormControl;
  reportid: FormControl;
  // data
  formSubscription?: Subscription;
  employeeExpenses?: Expense[]; // expenses for selected employee
  employeeReports?: Report[]; // Reports for selected employee
  reportExpenses?: Expense[]; // expenses matching report items keys
  selectedEmployee: Employee; // the current selected employee
  selectedReport: Report;

  employees: Employee[] = []; // all employees

  //misc
  msg: string;
  pickedEmployee: boolean;
  pickedReport: boolean;
  total: number;

  constructor(
    private builder: FormBuilder,
    private expenseService: ExpenseService,
    private employeeService: NewEmployeeService,
    private reportService: ReportService
  ) {
    this.msg = '';
    this.pickedEmployee = false;
    this.pickedReport = false;
    this.total = 0.0;
    this.employeeid = new FormControl('');
    this.reportid = new FormControl('');

    this.viewerForm = this.builder.group({
      employeeid: this.employeeid,
      reportid: this.reportid,
    });
    this.selectedEmployee = {
      id: 0,
      title: '',
      firstname: '',
      lastname: '',
      phoneno: '',
      email: '',
    };
    this.selectedReport = {
      id: 0,
      employeeid: 0,
      items: [], //Might need to be fixed
      datecreated: '',
    };
  } // constructor
  ngOnInit(): void {
    this.onPickEmployee(); // sets up subscription for dropdown click
    this.onPickReport();
    this.msg = 'loading employees from server...';
    this.getAllEmployees();
  } // ngOnInit
  ngOnDestroy(): void {
    if (this.formSubscription !== undefined) {
      this.formSubscription.unsubscribe();
    }
  } // ngOnDestroy

  getAllEmployees(passedMsg: string = ''): void {
    this.employeeService.getAll().subscribe({
      // Create observer object
      next: (employees: Employee[]) => {
        this.employees = employees;
      },
      error: (err: Error) =>
        (this.msg = `Couldn't get employees - ${err.message}`),
      complete: () =>
        passedMsg ? (this.msg = passedMsg) : (this.msg = `Employees loaded!`),
    });
  } // getAllEmployees
  onPickEmployee(): void {
    this.formSubscription = this.viewerForm
      .get('employeeid')
      ?.valueChanges.subscribe((val) => {
        this.selectedEmployee = val;
        this.loadEmployeeExpenses(val.id);
        this.loadEmployeeReports(val.id);
        this.msg = 'Employees reports loaded';
        this.pickedEmployee = true;
      });
  } // onPickEmployee

  onPickReport(): void {
    this.viewerForm.get('reportid')?.valueChanges.subscribe((val) => {
      this.selectedReport = val;
      this.total = 0;

      // retrieve just the expenses in the report
      if (this.employeeExpenses !== undefined) {
        this.reportExpenses = this.employeeExpenses.filter((expense) =>
          this.selectedReport?.items.some(
            (item) => item.expenseid === expense.id
          )
        );
      }
      this.pickedReport = true;
      if(this.reportExpenses) {
        this.reportExpenses.forEach((exp) => (this.total += exp.amount));
      }
      
    });
    //this.formSubscription?.add(reportSubscription); // add it as a child, so all can be destroyed together
  }

  loadEmployeeReports(id: number): void {
    this.msg = 'loading reports...';
    this.reportService
      .getSome(id)
      .subscribe((reports) => (this.employeeReports = reports));
  }

  /**
   * loadEmployeeExpenses - obtain a particular employee's expenses
   * we'll match the report expenses to them later
   */
  loadEmployeeExpenses(id: number): void {
    // expenses aren't part of the page, so we don't use async pipe here
    this.msg = 'loading expenses...';
    this.expenseService
      .getSome(id)
      .subscribe((expenses) => (this.employeeExpenses = expenses));
  }
  viewPdf(): void {
    window.open(`${PDFURL}${this.selectedReport.id}`, '');
        this.pickedEmployee = false;
        this.pickedReport = false;
        this.total = 0;
  } // viewPdf
}
