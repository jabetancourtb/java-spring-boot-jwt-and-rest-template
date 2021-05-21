export class CustomerModel {

    id: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;

    constructor(id: number, username: string, password: string, firstName: string, lastName: string) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public getId(): number {
        return this.id;
    }

    public getUsername(): string {
        return this.username;
    }

    public getPassword(): string {
        return this.password;
    }

    public getFirstName(): string {
        return this.firstName;
    }

    public getLastName(): string {
        return this.lastName;
    }
}