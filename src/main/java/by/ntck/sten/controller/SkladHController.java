package by.ntck.sten.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.ntck.sten.model.HistoryOperation;
import by.ntck.sten.model.Kladovshik;
import by.ntck.sten.model.SkladH;
import by.ntck.sten.service.IHistoryOperationService;
import by.ntck.sten.service.IKladovshikService;
import by.ntck.sten.service.ISkladHService;

@Controller
@RequestMapping("skladH")
public class SkladHController {
	public static  final Logger LOG = Logger.getLogger(SkladHController.class);
	
	private ISkladHService skladHService;	
	
	private IKladovshikService kladovshikService;	
	
	private IHistoryOperationService historyOperationService;
	

	@Autowired(required = true)
	@Qualifier(value = "skladHService")
	public void setSkladHService(ISkladHService skladHService) {
		this.skladHService = skladHService;
	}	
	
	@Autowired(required = true)
	@Qualifier(value = "historyOperationService")
	public void setHistoryOperationService(IHistoryOperationService historyOperationService) {
		this.historyOperationService = historyOperationService;
	}
	
	@Autowired(required = true)
	@Qualifier(value = "kladovshikService")
	public void setKladovshikService(IKladovshikService kladovshikService) {
		this.kladovshikService = kladovshikService;
	}
	
	public int skId = 0;
	
	@RequestMapping(value = "/skladsH/{id}")
	public String liStringSkladH(@PathVariable("id") int id ,Model model, HttpServletRequest request){
		model.addAttribute("skladH", new SkladH());
		model.addAttribute("skladsH", skladHService.skladHById(id));
		skId = id;

		return "skladH/skladsH";
	}
	
	@RequestMapping(value = "/skladH_in")
	public String in(Model model){
		model.addAttribute("skladH", new SkladH());
		return "skladH/skladH_create";
	}
	
	@RequestMapping(value = "/skladH_out")
	public String out(Model model){
		model.addAttribute("skladH", new SkladH());
		model.addAttribute("skladHList", skladHService.skladHById(skId));
		
		return "skladH/skladH_out";
	}
	
	public void history(int id, String Dates, int Id_row, String TableName,  String Operation,  int id_kladovshik){
		HistoryOperation historyOperation = new HistoryOperation();
		historyOperation.setId(id);
		historyOperation.setDate(Dates);
		historyOperation.setId_row(Id_row);
		historyOperation.setOperation(Operation);
		historyOperation.setTableName(TableName);		
		historyOperation.setKladovshik(kladovshikService.getById(id_kladovshik));
		this.historyOperationService.add(historyOperation);			
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("id") int id, @RequestParam("operthiya") String operthiya, @RequestParam("kol_vo") float kol_vo,
			@RequestParam("data_oper") String data_oper, @RequestParam("ttn") String ttn, @RequestParam("postavhik") String postavhik,
			@RequestParam("data_zayavki") String data_zayavki, @RequestParam("data_ttn") String data_ttn, @RequestParam("kod_zatrat") String kod_zatrat,
			@RequestParam("naim") String naim, @RequestParam("ttni") String ttni, @RequestParam("fio_zakazchika") String fio_zakazchika,
			@RequestParam("isdel") String isdel, @RequestParam("is_del") String is_del, @RequestParam("kol_vo_old") int kol_vo_old,
			@RequestParam("obrabotano") String obrabotano, @RequestParam("naim_doc") String naim_doc, @RequestParam("rep_in") String rep_in,
			@RequestParam("tab_nom_mol") String tab_nom_mol, @RequestParam("sklad_out_key") int sklad_out_key, @RequestParam("sklad_in_key") int sklad_in_key,
			@RequestParam("rep_status") int rep_status, @RequestParam("alt_edin") String alt_edin, @RequestParam("alt_kol_vo") float alt_kol_vo,
			@RequestParam("imports") String imports, @RequestParam("id_zayavka") int id_zayavka, @RequestParam("id_ord") int id_ord,			
			@RequestParam("real_data_oper") String real_data_oper, @RequestParam("link") int link, @RequestParam("master") String master,
			HttpServletRequest request){
		
		Date currentDate = new Date();
		SkladH skladH = new SkladH();		
		
		skladH.setId(id);
		skladH.setOperthiya(operthiya);
		skladH.setKol_vo(kol_vo);
		skladH.setData_oper(data_oper);		
		skladH.setTtn(ttn);
		skladH.setPostavhik(postavhik);
		skladH.setData_zayavki(data_zayavki);
		skladH.setData_ttn(data_ttn);
		skladH.setKod_zatrat(kod_zatrat);
		skladH.setNaim(naim);
		skladH.setTtni(ttni);
		skladH.setFio_zakazchika(fio_zakazchika);
		skladH.setIsdel(isdel);
		skladH.setIs_del(is_del);
		skladH.setKol_vo_old(kol_vo_old);
		skladH.setObrabotano(obrabotano);
		skladH.setNaim_doc(naim_doc);
		skladH.setRep_in(rep_in);
		skladH.setTab_nom_mol(tab_nom_mol);
		skladH.setSklad_out_key(sklad_out_key);
		skladH.setSklad_in_key(sklad_in_key);
		skladH.setRep_status(rep_status);
		skladH.setAlt_edin(alt_edin);
		skladH.setAlt_kol_vo(alt_kol_vo);
		skladH.setImport_(imports);
		skladH.setId_zayavka(id_zayavka);
		skladH.setId_ord(id_ord);
		skladH.setReal_data_oper(real_data_oper);
		skladH.setLink(link);
		skladH.setMaster(master);
		skladH.setSklad_id(skId);		
		skladHService.add(skladH);
		
		int id_kladovshik = ((Kladovshik)request.getSession().getAttribute("kladovshik")).getId();	
		this.history(0, currentDate.toString(), skladH.getId(), "Sklad", operthiya, id_kladovshik );
		
		return "redirect:/skladH/skladsH/"+ skId;
	}
	
	@RequestMapping(value = "/adds", method = RequestMethod.POST)
	public String add(@RequestParam("id") int id,  @RequestParam("kol_vo") float kol_vo, HttpServletRequest request, Model model){
		
		Date currentDate = new Date();
		SkladH skladH = new SkladH();		

		skladH.setId(id);
		skladH.setOperthiya("out");
		skladH.setKol_vo(skladHService.Kol(id).getKol_vo() - kol_vo );
		skladH.setData_oper(currentDate.toString());		
		
		skladH.setSklad_id(skId);		
		skladHService.update(skladH);
		
		
		int id_kladovshik = ((Kladovshik)request.getSession().getAttribute("kladovshik")).getId();	
		this.history(0, currentDate.toString(), skladH.getId(), "Sklad", "out", id_kladovshik );
		
		return "redirect:/skladH/skladsH/"+ skId;
	}

}