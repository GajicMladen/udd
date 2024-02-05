import { HttpEvent, HttpInterceptorFn, HttpResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { LoaderService } from '../services/loader-service/loader.service';
import { catchError, map } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

export const loaderInterceptor: HttpInterceptorFn = (req, next) => {
  const _loading = inject(LoaderService);
  const toastr = inject(ToastrService);
  
  _loading.setLoading(true, req.url);
  return next(req)
    .pipe(catchError((err) => {
      _loading.setLoading(false, req.url);
      toastr.error(err.message);
      return err;
    }))
    .pipe(map<any, any>((evt:any) => {
      if (evt instanceof HttpResponse) {
        _loading.setLoading(false, req.url);
      }
      return evt;
    }));
};
