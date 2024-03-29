export interface Contract{
    id:string,
    nameSignGov:string,
    lastnameSignGov:string,
    nameSignAgency:string,
    lastnameSignAgency:string,
    govName:string,
    govLevel:string,
    addressGov:string,
    addressAgency:string,
    emailGov:string,
    emailAgency:string,
    title:string,
    content:string,
    location:string | undefined,
    serverFilename:string,
    idBP:number,
    highlight:string
}