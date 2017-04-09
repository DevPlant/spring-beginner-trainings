import {Injectable} from "@angular/core";
import {Http, Response, URLSearchParams} from "@angular/http";
import {Greeting} from "./app.component";
import {Observable} from "rxjs/Rx";
import {environment} from "../environments/environment";

@Injectable()
export class GreetingService {

  constructor(private http: Http) {
  }

  sayHello(name: string): Observable<Greeting> {
    let params: URLSearchParams = new URLSearchParams();
    params.set('who', name);

    return this.http.get(environment.apiEndpoint + "hello", {search: params}).map((res: Response) => res.json());
  }

  sayGoodbye(): Observable<Greeting> {
    return this.http.get(environment.apiEndpoint + "goodbye").map((res: Response) => res.json());
  }


}
