import { Employee } from "./employee";

export class Project {
    projectId!: number;
    name!: string;
    stage!: string;
    description!: string;
    employees!: Employee[];
}
