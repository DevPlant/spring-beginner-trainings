import {Component, OnInit} from "@angular/core";
import {LoginGuard} from "./route/guard/login.guard";
import {RegisterGuard} from "./route/guard/register.guard";
import {UserService} from "./user/user.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}

