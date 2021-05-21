import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerDTO } from "src/app/models/customer/customerDto.model";
import { CustomerModel } from "src/app/models/customer/customer.model";
import { SignInDTO } from "src/app/models/sign-in/signInDto.model";
import { environment } from "src/environments/environment";
import { CookieService } from "ngx-cookie-service";
import { JwtModel } from "src/app/models/jwt/jwt.model";


@Injectable({
    providedIn: 'root'
})

export class CustomerService {

    constructor(private http : HttpClient, private cookieService : CookieService) { }

    singIn(signInDto: SignInDTO) : Promise<JwtModel> {
        let apiUrl : string = environment.API_URL_DEVELOPMENT + "/customer/sign-in";
        return new Promise((resolve, reject) => {
            this.http.post<JwtModel>(apiUrl, signInDto).subscribe(data => {
                    this.cookieService.set(environment.AUTHORIZATION_HEADER, data.jwt, { expires : 5/24 });
                    resolve(data);
                }, 
                (error) => {
                    reject(error);
                }
            );
        });
    }

    signUp(userDto: CustomerDTO) : Promise<CustomerModel> {
        let apiUrl : string = environment.API_URL_DEVELOPMENT + "/customer";
        return new Promise((resolve, reject) => {
             this.http.post<CustomerModel>(apiUrl, userDto).subscribe(data => {
                    resolve(data);
                }, 
                (error) => {
                    reject(error);
                }
            ); 
        });
    }

    getCustomerFromRestClient(customerId: number) : Promise<CustomerDTO> {
        let apiUrl : string = environment.API_URL_DEVELOPMENT + "/customer/" + customerId;
        return new Promise((resolve, reject) => {
             this.http.get<CustomerDTO>(apiUrl).subscribe(data => {
                    resolve(data);
                }, 
                (error) => {
                    reject(error);
                }
            ); 
        });
    }

    logout(): void {
        this.cookieService.deleteAll();
    }
}
