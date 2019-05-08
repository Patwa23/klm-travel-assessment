import { Location } from "@angular/common";

export class Fare {
    public amount: number;
    public currency: string;
    public origin: Location;
    public destination: Location;

    constructor(amount: number, currency: string, origin: Location, destination: Location) {
        this.amount = amount;
        this.currency = currency;
        this.origin = origin;
        this.destination = destination;
    }
}