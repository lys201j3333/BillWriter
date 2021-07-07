/*
 * @Author: your name
 * @Date: 2021-06-15 03:19:27
 * @LastEditTime: 2021-06-23 23:59:06
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \frontend\src\pages\base\try\index.tsx
 */
import { Line } from '@ant-design/charts';
import React, { useCallback,  useState } from 'react';
import SearchFilter from './SearchFilter';
import { DEFAULT_SEARCH_PROPS } from '@/constants';

export default function ViewPage() {
  const [searchProps, changeSearchProps] = useState<defs.ProductQueryDTO>({
    ...DEFAULT_SEARCH_PROPS,
  });
  const listByYears = useCallback((props)=>{
    API.product.bill.request(props).then((data)=>{
      setmoneylist(data);});},[]);
  const [data1,setmoneylist] = useState<Array<ObjectMap>>([]);
  console.dir(data1);
  const Page: React.FC = () => {
    // const data = [
    //   { year: '1991', value: 3 },
    //   { year: '1992', value: 4 },
    //   { year: '1993', value: 3.5 },
    //   { year: '1994', value: 5 },
    //   { year: '1995', value: 4.9 },
    //   { year: '1996', value: 6 },
    //   { year: '1997', value: 7 },
    //   { year: '1998', value: 9 },
    //   { year: '1999', value: 13 },
    // ];
  
    const config = {
      data:data1,
      height: 400,
      xField: 'smonth',
      yField: 'sumprice',
      point: {
        size: 5,
        shape: 'diamond',
      },
    };
    return <Line {...config} />;
  };
  return (
    <>
        <SearchFilter
          searchProps={searchProps}
          changeSearchProps={(props) => {
            changeSearchProps({
              ...searchProps,
              ...props,
            });
          }}
          onSearch={() => {
            listByYears(searchProps);
          }}
        />
<Page/>
</>
  );
}