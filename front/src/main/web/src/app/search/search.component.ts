import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { map, flatMap, toArray} from 'rxjs/operators';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
    SERVER_API_URL = 'http://localhost:8080';
    topicList: string[];
    constructor(private http: HttpClient) {

    }

    ngOnInit() {
    }

    getTopicList(){
       this.http.get<any[]>("/topics?searchString=Topic").pipe(
           flatMap(topics => topics),
           map(topic => topic.name),
           toArray()
       ).subscribe(topics => this.topicList = topics);
    }
}
