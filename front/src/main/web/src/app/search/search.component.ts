import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-search',
    templateUrl: './search.component.html',
    styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
    topicList: string[];
    constructor() { }

    ngOnInit() {
    }

    getTopicList(){
        this.topicList =  ['topic1', 'topic2', 'topic3'];
    }
}
