import { Coordinates } from "./coordinates.model";

export class Location {
    public code: string;
    public name: string;
    public description: string;
    public coordinates: Coordinates;
    public parent: Location;

    constructor(code: string, name: string, description: string, coordinates: Coordinates, parent: Location) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.parent = parent;
    }
}