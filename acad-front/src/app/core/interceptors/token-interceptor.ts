import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  //const token = localStorage.getItem('token');
  const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbHZpcyIsImlkIjoxLCJpYXQiOjE3NTAzNDA3MDQsImV4cCI6MTc1MDM0NDMwNH0.d2WjFl6QCFPGnuUSPOIIkvhNwVMnQrcxhUOkpyjmHaA";

  if (token) {
    const authReq = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
    return next(authReq);
  }

  return next(req);
};
