import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer/customer.service';
import { CustomerModel } from 'src/app/models/customer/customer.model';
import { ToastrService } from 'ngx-toastr';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {

  customer: CustomerModel;

  getCustomerForm = new FormGroup({
    id : new FormControl(null, Validators.required),
  });


  constructor(private customerService: CustomerService, 
    private toastr: ToastrService) { }

    ngOnInit(): void {
    
    }

    public getCustomerFromRestClient() {
      let customerId = +this.getCustomerForm.get('id').value

      let promise = this.customerService.getCustomerFromRestClient(customerId);
  
      promise.then(data => {
        this.customer = data;
      });
  
      promise.catch(error => {
        this.toastr.error(error.error.message);
      });
    }

}
