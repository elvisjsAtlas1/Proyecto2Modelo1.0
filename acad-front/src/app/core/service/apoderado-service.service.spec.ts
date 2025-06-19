import { TestBed } from '@angular/core/testing';

import { ApoderadoServiceService } from './apoderado-service.service';

describe('ApoderadoServiceService', () => {
  let service: ApoderadoServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApoderadoServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
