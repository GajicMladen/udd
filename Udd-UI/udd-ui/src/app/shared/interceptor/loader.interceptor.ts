import { HttpEvent, HttpInterceptorFn, HttpResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoaderService } from '../services/loader-service/loader.service';
import { catchError, map } from 'rxjs';

export const loaderInterceptor: HttpInterceptorFn = (req, next) => {
  const _loading = inject(LoaderService);
  _loading.setLoading(true, req.url);
  return next(req)
    .pipe(catchError((err) => {
      _loading.setLoading(false, req.url);
      return err;
    }))
    .pipe(map<any, any>((evt:any) => {
      if (evt instanceof HttpResponse) {
        _loading.setLoading(false, req.url);
      }
      return evt;
    }));
};
