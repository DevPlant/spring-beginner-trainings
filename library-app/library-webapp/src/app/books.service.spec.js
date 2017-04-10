"use strict";
var testing_1 = require('@angular/core/testing');
var books_service_1 = require('./books.service');
describe('BooksService', function () {
    beforeEach(function () {
        testing_1.TestBed.configureTestingModule({
            providers: [books_service_1.BooksService]
        });
    });
    it('should ...', testing_1.inject([books_service_1.BooksService], function (service) {
        expect(service).toBeTruthy();
    }));
});
