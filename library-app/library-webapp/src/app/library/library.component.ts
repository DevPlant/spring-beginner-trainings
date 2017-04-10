import {Component, OnInit} from "@angular/core";
import {UserService} from "../user/user.service";

@Component({
  selector: 'app-library',
  providers: [UserService],
  templateUrl: './library.component.html',
  styleUrls: ['./library.component.css']
})
export class LibraryComponent implements OnInit {
  private userService: UserService;

  constructor(userService: UserService) {
    this.userService = userService;
  }

  ngOnInit() {
  }

  logout() {
    this.userService.logout();
  }

}
