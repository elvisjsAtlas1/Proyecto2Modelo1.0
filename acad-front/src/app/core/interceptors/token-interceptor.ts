import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  //const token = localStorage.getItem('token');
  const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBRE1JTjEiLCJpZCI6MTYsImlhdCI6MTc1MTQxNDUxNCwiZXhwIjoxNzUxNDE4MTE0fQ.TfDiwyt5BRGWf_fJP5r6fwuEolTy86cifvMa3WsK7ms";

  if (token) {
    const authReq = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
    return next(authReq);
  }

  return next(req);
};
