import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
 export const notify = (type,message) => {
  if(type === 'success'){
      toast.success(message,{position: toast.POSITION.TOP_RIGHT,autoClose:8000})
  }
  if(type === 'error'){

       toast.error(message,{position: toast.POSITION.TOP_RIGHT,autoClose:false,theme: "colored"})
  }
  if(type === 'warning'){

       toast.warn(message,{position: toast.POSITION.TOP_RIGHT,autoClose:8000,theme: "colored"})
  }
  if(type === 'info'){

   toast.info(message,{position: toast.POSITION.TOP_RIGHT,autoClose:8000,theme: "colored"})
}
}
