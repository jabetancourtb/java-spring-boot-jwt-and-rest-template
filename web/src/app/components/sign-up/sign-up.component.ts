import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CustomerModel } from 'src/app/models/customer/customer.model';
import { CustomerDTO } from 'src/app/models/customer/customerDto.model';
import { CustomerService } from 'src/app/services/customer/customer.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

  customer: CustomerModel;

  customerForm = new FormGroup({
    email : new FormControl(null, Validators.required),
    password : new FormControl(null, Validators.required),
    firstName : new FormControl(null, Validators.required),
    lastName : new FormControl(null, Validators.required),
    repeatPassword : new FormControl(null, Validators.required),
  });

  constructor(private customerService: CustomerService
    , private toastr: ToastrService
    , private router: Router) { }

  ngOnInit(): void {
    
  }

  public checkPassword()  {
    if (this.customerForm.get('password').value == this.customerForm.get('repeatPassword').value) {
      this.signUp();
    } else {
      this.toastr.error('The password does not match');
    }
  }

  public signUp() {
    const customerDto = new CustomerDTO(this.customerForm.get('email').value, this.customerForm.get('password').value
    , this.customerForm.get('firstName').value, this.customerForm.get('lastName').value);

    let promise = this.customerService.signUp(customerDto);

    promise.then(data => {
      this.customer = data;
      this.toastr.success('The customer has been registered');

      setTimeout(() => {
        this.router.navigate(['/sign-in']);
      }, 2000);
    });

    promise.catch(error => {
      this.toastr.error(error.error.message);
    });
  }

  public goToLogin(): void {
    this.router.navigateByUrl('/sign-in');
}
}
