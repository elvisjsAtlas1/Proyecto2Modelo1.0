import { TestBed } from '@angular/core/testing';

import { ApoderadoService } from './apoderado.service';

describe('AntecedenteMedicoService', () => {
  let service: ApoderadoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApoderadoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
