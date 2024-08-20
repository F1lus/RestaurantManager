export enum Severity {
  SUCCESS,
  WARNING,
  ERROR,
  INFO
}

export interface Notification {
  title: string;
  text: string;
  severity: Severity;
}
