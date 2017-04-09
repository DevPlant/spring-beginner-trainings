import {Component} from "@angular/core";
import {GreetingService} from "./greeting.service";


@Component({
  selector: 'app-root',
  providers: [GreetingService],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  viewState = ViewState;
  state: ViewState;
  helloGreeting: Greeting;
  goodbyeGreeting: Greeting;
  greeting: Greeting;

  constructor(private greetingService: GreetingService) {
    this.state = ViewState.Initial;
    this.greeting = new Greeting();
    this.helloGreeting = new Greeting();
    this.goodbyeGreeting = new Greeting();
  }

  sayHello() {
    this.greetingService.sayHello(this.greeting.message).subscribe(response => {
      this.helloGreeting = response;
    });
    this.state = ViewState.Hello;
    this.greeting = new Greeting();
  }

  sayGoodbye() {
    this.greetingService.sayGoodbye().subscribe(response => {
      this.goodbyeGreeting = response;
    });
    this.state = ViewState.Goodbye;
  }

  tryAgain() {
    this.state = ViewState.Initial;
  }
}

export class Greeting {
  message: string;

}

enum ViewState {

  Initial, Hello, Goodbye
}
