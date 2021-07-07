/*
 * @Author: your name
 * @Date: 2021-04-29 20:36:56
 * @LastEditTime: 2021-06-21 21:55:52
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \frontend\src\pages\base\current\SearchFilter\index.tsx
 */
import { Input} from 'antd';
import styles from '@/styles/search-filter.less';
interface SearchFilterProps {
  onSearch: () => void;
  searchProps: defs.ProductQueryDTO;
  changeSearchProps: (searchProps: defs.ProductQueryDTO) => void;
}

export default function SearchFilter(props: SearchFilterProps) {
  return (
    <div className={styles.filterPanel}>
      <div className={styles.filterItem}>
      
        <span className={styles.label}>Year：</span>
        
        <Input.Search
          allowClear={true}
          value={props.searchProps.syear}
          onSearch={props.onSearch}
          onChange={(e) =>
            props.changeSearchProps({
              syear: Number(e.target.value,)
            })
          }
        />
       
      </div>
    </div>
  );
}
