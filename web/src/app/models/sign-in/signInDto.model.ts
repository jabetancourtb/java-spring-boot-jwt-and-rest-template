export class SignInDTO {
    
    username: string;
    plainPassword: string;

    constructor(username: string, plainPassword: string) {
        this.username = username;
        this.plainPassword = plainPassword;
    }
    
    public getUsername(): string {
        return this.username;
    }

    public getPlainPassword(): string {
        return this.plainPassword;
    }
}