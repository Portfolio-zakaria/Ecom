import React from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import './style.css'
import { createStore } from 'redux'
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux'
import rootReducers from './redux/reducers';
let store = createStore(rootReducers,window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__())
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <Provider store={store}>

        <BrowserRouter>
            <App />
        </BrowserRouter>
      
    </Provider>
);

