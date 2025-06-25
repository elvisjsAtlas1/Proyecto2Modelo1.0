import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  //const token = localStorage.getItem('token');
  const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBRE1JTiIsImlkIjoyLCJpYXQiOjE3NTA4NjA4MDEsImV4cCI6MTc1MDg2NDQwMX0.KZ4nfVprRNRQBQV0EscJ80Nf3xjHsvnqPTTkmw5P8Vs";

  if (token) {
    const authReq = req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
    return next(authReq);
  }

  return next(req);
};
