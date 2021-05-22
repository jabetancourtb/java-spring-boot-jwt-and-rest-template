export class CustomerDTO {
    
    username: string;
    plainPassword: string;
    firstName: string;
    lastName: string;

    constructor(username: string, plainPassword: string, firstName: string, lastName: string) {
        this.username = username;
        this.plainPassword = plainPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public getUsername(): string {
        return this.username;
    }

    public getPlainPassword(): string {
        return this.plainPassword;
    }

    public getFirstName(): string {
        return this.firstName;
    }

    public getLastName(): string {
        return this.lastName;
    }
}