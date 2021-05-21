import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer/customer.service';
import { CustomerModel } from 'src/app/models/customer/customer.model';
import { ToastrService } from 'ngx-toastr';
import { CustomerDTO } from 'src/app/models/customer/customerDto.model';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {

  customer: CustomerDTO;

  constructor(private customerService: CustomerService, 
    private toastr: ToastrService) { }

    ngOnInit(): void {
    let promise = this.customerService.getCustomerFromRestClient(1);

    promise.then(data => {
      this.customer = data;
    });

    promise.catch(error => {
      this.toastr.error('The customer was not found');
    })
  }

}
