import { Component, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { FileUploadService } from '../../services/file-upload-service/file-upload.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { CommonModule } from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
@Component({
  selector: 'app-upload-file',
  standalone: true,
  imports: [CommonModule,MatButtonModule],
  templateUrl: './upload-file.component.html',
  styleUrl: './upload-file.component.css'
})
export class UploadFileComponent {

  @Input() addressToUpload : string = "";

  selectedFiles: FileList | undefined;
  currentFile: File | undefined;
  progress = 0;
  message = '';
  srcResult: string = "";
  fileInfos!: Observable<any>;

  constructor(
    private uploadService: FileUploadService,
    private toastr: ToastrService) { }

  selectFile(event:any) {  

    this.selectedFiles = event.target.files;
    this.srcResult = this.selectedFiles![0].name;
  }

  upload() {
    this.progress = 0;
  
    this.currentFile = this.selectedFiles?.item(0) ? this.selectedFiles.item(0)! : undefined;
    if(this.currentFile){
      
      this.uploadService.upload(this.addressToUpload,this.currentFile).subscribe(
        data =>{
          console.log(data);
          this.message = data;
        }
      )
      }
    this.currentFile = undefined;
    this.selectedFiles = undefined;
  }
}
