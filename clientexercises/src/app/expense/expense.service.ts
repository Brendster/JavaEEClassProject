import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GenericHttpService } from '@app/generic-http.service';
import { Expense } from './expense';
@Injectable({
  providedIn: 'root',
})
export class ExpenseService extends GenericHttpService<Expense> {
  constructor(httpClient: HttpClient) {
    super(httpClient, `expenses`);
  } // constructor
} // ExpenseService
