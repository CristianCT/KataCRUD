import React, { useContext, createContext, useReducer, useEffect, useRef, useState } from "react";

const HOST_API = "http://localhost:8080/api";

const initialState = {
  list: []
};

const Store = createContext(initialState);

/* Componente para presentar el formulario e ingresar un nuevo registro */
const Form = () => {
  const formRef = useRef(null);
  const { dispatch } = useContext(Store);
  const [state, setState] = useState();

  const onAdd = (event) => {
    event.preventDefault();

    const request = {
      name: state.name,
      id: null,
      completed: false
    };

    /* Petici{on al BackEnd para agregar un nuevo registro */
    fetch(HOST_API + "/todo", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => response.json())
      .then((todo) => {
        dispatch({ type: "add-item", item: todo });
        setState({ name: "" });
        formRef.current.reset();
      });
  }

  /* Renderizado de la plantilla */
  return <form ref={formRef}>
    <input
      type="text"
      name="name"
      onChange={(event) => {
        setState({ ...state, name: event.target.value })
      }}  ></input>
    <button onClick={onAdd}>Agregar</button>
  </form>
}

/* Componente para mostrar la lista de registros */
const List = () => {
  const { dispatch, state} = useContext(Store);
  
  
  useEffect(() => {
    /* Petici{on al BackEnd para solicitar la lista de todos los registros */
    fetch(HOST_API + "/todos")
    .then(response => response.json())
    .then((list) => {
      dispatch({ type: "update-list", list })
    });
  }, [state.list.length, dispatch]);

  /* Renderizado de la plantilla */
  return <div>
    <table >
      <thead>
        <tr>
          <td>ID</td>
          <td>Tarea</td>
          <td>¿Completado?</td>
        </tr>
      </thead>
      <tbody>
        {state.list.map((todo) => {
          return <tr key={todo.id}>
            <td>{todo.id}</td>
            <td>{todo.name}</td>
            <td>{todo.isCompleted}</td>
          </tr>
        })}
      </tbody>
    </table>
  </div>
}

function reducer(state, action){
  switch(action.type){
    case 'update-list':
      return {...state, list: action.list}
    case 'add-item':
      const newList = state.list;
      newList.push(action.item);
      return {...state, list: newList};
    default:
      return state;
  }
}

const StoreProvider = ({children}) => {
  
  const [state, dispatch] = useReducer(reducer, initialState);
  return <Store.Provider value={{ state, dispatch }}>
    { children }
  </Store.Provider>
}

/* Aplicación principal */
function App() {
  return (
    <StoreProvider>
      <Form/>
      <List/>
    </StoreProvider>
  );
}

export default App;
