import { Input } from 'antd';
import styles from '@/styles/search-filter.less';
import {  DatePicker,Space} from 'antd';
import moment from 'moment';
interface SearchFilterProps {
  onSearch: () => void;
  searchProps: defs.ProductQueryDTO;
  changeSearchProps: (searchProps: defs.ProductQueryDTO) => void;
}
const { RangePicker } = DatePicker;
export default function SearchFilter(props: SearchFilterProps) {
  return (
    <div className={styles.filterPanel}>
      <div className={styles.filterItem}>
        <span className={styles.label}>TIME：</span>
        <RangePicker 
          showTime 
          onChange={(e)=>{
            props.changeSearchProps({
            ftime: moment(e?.[0]).format("YYYY-MM-DD hh:mm:ss"),
            ltime: moment(e?.[1]).format("YYYY-MM-DD hh:mm:ss"),
            })
           }
          }
          onBlur={props.onSearch}
          />
      </div>
    </div>
  );
}
