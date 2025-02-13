import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GenericHttpService } from '@app/generic-http.service';
import { Report } from './report';
@Injectable({
  providedIn: 'root'
})
export class ReportService extends GenericHttpService<Report>{

  constructor(httpClient: HttpClient) {
    super(httpClient, `reports`);
  } // constructor
}
