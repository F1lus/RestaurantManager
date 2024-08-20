import {AccountType, ProtectionLevel} from "./auth";

export interface NavLink {
  name: string;
  link: string;
  protection: ProtectionLevel;
  accountType?: AccountType;
}
