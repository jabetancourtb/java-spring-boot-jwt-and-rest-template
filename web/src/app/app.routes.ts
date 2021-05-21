import { Routes, RouterModule } from "@angular/router";
import { SignInComponent } from "./components/sign-in/sign-in.component";
import { SignUpComponent } from "./components/sign-up/sign-up.component";
import { CustomerComponent } from "./components/customer/customer.component";
import { AuthGuard } from "./guard/auth.guard";

const APP_ROUTES: Routes = [
    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'customer', component: CustomerComponent, canActivate: [AuthGuard] },
    { path: '**', pathMatch: 'full', redirectTo: 'sign-in' }
];

export const APP_ROUTING = RouterModule.forRoot(APP_ROUTES, { useHash: true });