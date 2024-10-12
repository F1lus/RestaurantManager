import {ProfileType, ProtectionLevel} from "./auth";

export interface NavLink {
  order: number;
  name: string;
  link: string;
  protection: ProtectionLevel;
  accountType?: ProfileType;
}
