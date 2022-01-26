import { Project } from "./project";

export class Employee {
    employeeId!: number;
    firstName!: string;
    lastName!: string;
    emailId!: string;
    projects!: Project[];
}
