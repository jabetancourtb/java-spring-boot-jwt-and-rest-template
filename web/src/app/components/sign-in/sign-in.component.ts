import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { CustomerModel } from 'src/app/models/customer/customer.model';
import { SignInDTO } from 'src/app/models/sign-in/signInDto.model';
import { CustomerService } from 'src/app/services/customer/customer.service';
import { JwtParseService } from 'src/app/services/util/jwtParse.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  customer: CustomerModel;
  public jwtData : object;

  loginForm = new FormGroup({
    email : new FormControl(null, Validators.required),
    password : new FormControl(null, Validators.required)
  });

  constructor(private customerService: CustomerService
    , private toastr: ToastrService
    , private router: Router
    , private jwtParseService: JwtParseService) { }

  ngOnInit(): void { 
  }

  public login(): void {

    const signInDTO = new SignInDTO(this.loginForm.get('email').value, this.loginForm.get('password').value)

    let promise = this.customerService.singIn(signInDTO);

    promise.then(data => {
      this.jwtData = this.jwtParseService.parseJWT();
      this.toastr.success('Welcome ' + this.jwtData['fullname']);
      this.router.navigate(['customer']);
    });

    promise.catch(error => {
      this.toastr.error('The credentials are wrong');
    });
  }

  public goToSignUp(): void {
      this.router.navigateByUrl('/sign-up');
  }
}
