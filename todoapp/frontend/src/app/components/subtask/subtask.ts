import { Component, inject, OnInit } from '@angular/core';
import { SubtaskService } from '../../services/subtask-service';
import { AsyncPipe } from '@angular/common';
import { SubtaskModel } from '../../models/subtask-model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-subtask',
  imports: [AsyncPipe, FormsModule],
  templateUrl: './subtask.html',
  styleUrl: './subtask.css',
})
export class Subtask implements OnInit{

  subtaskService = inject(SubtaskService)

  editingSubtaskId: number | null = null;
  editedTitle = '';

  ngOnInit(): void {
    this.subtaskService.getSubtasks();
  }

  addSubtask(title: string){
    const subtask: Partial<SubtaskModel> = {
      title: title,
      completed: false
    };
    this.subtaskService.addSubtask(subtask).subscribe(() => {
      this.subtaskService.getSubtasks();
    });
  }

  deleteSubtask(subtask: SubtaskModel){
    this.subtaskService.deleteSubtask(subtask).subscribe(() => {
      this.subtaskService.getSubtasks();
    });
  }

  editSubtask(subtask: SubtaskModel) {
    this.editingSubtaskId = subtask.subtaskId;
    this.editedTitle = subtask.title;
  }

  saveSubtask(subtask: SubtaskModel) {
    const updatedSubtask: SubtaskModel = {
      ...subtask,
      title: this.editedTitle
    };
    this.subtaskService.updateSubtask(updatedSubtask).subscribe(() => {
      this.editingSubtaskId = null;
      this.editedTitle = '';
      this.subtaskService.getSubtasks();
    });
  }

  cancelEdit() {
    this.editingSubtaskId = null;
    this.editedTitle = '';
  }

  toggleSubtaskCompleted(subtask: SubtaskModel, completed: boolean){
    const updatedSubtask: SubtaskModel = {
      ...subtask,
      completed: completed
    };

    this.subtaskService.updateSubtask(updatedSubtask).subscribe(() => {
      this.subtaskService.getSubtasks();
    });
  }

}
