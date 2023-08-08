import React, { useState } from "react";
import { getProducts } from "./ApiCore";
import CardC from "./CardC";

const Search = ({ categories }) => {
  const [searchData, setSearchData] = useState({ search: "", category: "" });
  const [products, setProducts] = useState([]);
  const handleChange = (e) => {
    setSearchData({ ...searchData, [e.target.id]: e.target.value });
  };
  const resultMessage = () => {
    return (
      products &&
      products.length > 0 && <h3>Found {products.length} Product(s)</h3>
    );
  };

  const searchSubmit = (e) => {
    e.preventDefault();

    let { search, category } = searchData;

    if (search || category) {
      getProducts({ search: search || undefined, category }).then((res) =>
        setProducts(res)
      );
    } else {
      setProducts([]);
    }
  };

  return (
    <div>
      <form onSubmit={searchSubmit}>
        <div className="input-group input-group-lg">
          <div className="input-group-prepend">
            <select onChange={handleChange} id="category" className="btn">
              <option value={""}>select a category</option>
              {categories.map((category) => (
                <option key={category._id} value={category._id}>
                  {category.name}
                </option>
              ))}
            </select>
          </div>
          <input
            type={"search"}
            onChange={handleChange}
            id="search"
            className="form-control mx-4"
          />
          <div className="input-group-append">
            <button className="btn">Search</button>
          </div>
        </div>
      </form>
      <hr />
      {resultMessage()}
      <div className="row">
        {products.map((product, i) => (
          <div key={product._id} className="col-md-4">
            <CardC product={product} />
          </div>
        ))}
      </div>
    </div>
  );
};

export default Search;
